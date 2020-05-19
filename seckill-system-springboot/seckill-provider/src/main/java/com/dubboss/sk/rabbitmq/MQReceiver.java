package com.dubboss.sk.rabbitmq;


import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultStatus;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.service.SkService;
import com.dubboss.sk.service.impl.RedisService;
import com.dubboss.sk.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

	private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

	@Autowired
	RedisService redisService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	SkService skService;

	@Autowired
	private GoodsService goodsServiceRpc;

//		@Autowired
//        MiaoShaMessageService messageService ;

	@RabbitListener(queues = RabbitMqConfig.MIAOSHA_QUEUE)
	public void receive(String message) {
		log.info("receive message:" + message);
		SkMessage skMessage = RedisService.strtoBean(message, SkMessage.class);
		SkUser skUser = skMessage.getSkUser();
		Long goodsId = skMessage.getGoodsId();

		// 判断库存
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		int stock = goods.getStockCount();
		if (stock <= 0) {
			return;
		}

		//判断是否已经秒杀到了
		SkOrder skOrder = orderService.getSkOrderByUIdGId(skUser.getId(), goodsId);
		if (skOrder != null) {
			return;
		}

		//减库存 下订单 写入秒杀订单
		skService.sk(skUser, goods);
	}



//	@RabbitListener(queues=MQConfig.MIAOSHATEST)
//	public void receiveMiaoShaMessage(Message message, Channel channel) throws IOException {
//		log.info("接受到的消息为:{}",message);
//		String messRegister = new String(message.getBody(), "UTF-8");
//		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//		MiaoShaMessageVo msm  = RedisService.stringToBean(messRegister, MiaoShaMessageVo.class);
//		messageService.insertMs(msm);
//		}
}
