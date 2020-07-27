# 秒杀项目中的各种场景

## 链接暴露

解决方案：URL动态化，MD5加密 + 盐值

前端先请求path接口，拿到动态生成加密后的秒杀接口，再请求接口{path}/do_miaosha

```java
@RequestMapping(value = "{path}/do_miaosha")
@ResponseBody
public ResultSk<Integer> miaosha(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId, @PathVariable("path") String path) {
	...
    //验证path
    boolean check = skService.checkPath(skUser, goodsId, path);
    if (!check) {
        result.withError(REQUEST_ILLEGAL.getCode(), REQUEST_ILLEGAL.getMessage());
        return result;
    }
	...
}


@AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
@RequestMapping(value = "path")
@ResponseBody
public ResultSk<String> skPath(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId, @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode) {
    ResultSk<String> result = ResultSk.build();
    model.addAttribute("user", skUser);
    if (skUser == null) {
        result.withError(ResultStatus.SESSION_ERROR.getCode(), ResultStatus.SESSION_ERROR.getMessage());
        return result;
    }
    boolean check = skService.checkVerifyCode(skUser, goodsId, verifyCode);
    if (!check) {
        result.withError(REQUEST_ILLEGAL.getCode(), REQUEST_ILLEGAL.getMessage());
        return result;
    }
    String path = skService.createSkPath(skUser, goodsId);
    result.setData(path);
    return result;
}

@Override
public String createSkPath(SkUser skUser, long goodsId) {
    String str = MD5Util.md5(UUIDUtil.generateUuid() + "123456");
    redisService.set(SkKey.getSkPath, "" + skUser.getId() + "_" + goodsId, str);
    return str;
}

```

## 资源静态化

在前后端分离的项目中，页面一般都是不会经过后端的，前端也有自己的服务器，所以提前把能放入CDN服务器的东西都放进去，减少真正秒杀时候服务器的压力。

## 验证码

加入验证码或者滑块，防止秒杀的时候请求一起打过来。

## 超卖

原因：将商品的库存加载到Redis中，如果秒杀成功，再异步的修改库存。假如此时库存仅剩一个，四台服务器一起查询都发现还有一个，都去扣库存，此时库存变为-3，造成超卖。

解决方案：MySQL使用版本（CAS原理）

```mysql
select version from goods WHERE id= 1001
update goods set num = num - 1, version = version + 1 WHERE id = 1001 AND num > 0 AND version = @version(上面查到的version)
```

优化：使用Lua脚本，将判断库存和扣减库存都写在一个脚本中由Redis执行，如果return false则证明库存已减为0。