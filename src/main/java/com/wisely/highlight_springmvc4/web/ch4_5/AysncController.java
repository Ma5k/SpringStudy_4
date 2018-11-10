package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.wisely.highlight_springmvc4.service.PushService;

/**
 * 	异步任务的实现是通过控制器从另一个线程返回一个DeferredResult，这里的DeferredResult是从pushService中获得的。
 * @author Mask
 *
 */
@Controller
public class AysncController {
	@Autowired
	PushService pushService;	//1、定时任务，定时更新DeferredResult
	
	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall() {	//2、返回给客户端DeferredResult
		return pushService.getAsyncUpdate();
	}
}
