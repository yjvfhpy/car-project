package com.saturn.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.saturn.api.ICallbackListener;
import com.saturn.api.ICallbackService;


@Service("callbackService")
public class CallbackServiceImpl implements ICallbackService {

	private final Map<String, ICallbackListener> listeners = new ConcurrentHashMap<String, ICallbackListener>();

	public CallbackServiceImpl() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						for (Map.Entry<String, ICallbackListener> entry : listeners.entrySet()) {
							try {
								entry.getValue().changed(getChanged(entry.getKey()));
							} catch (Throwable t) {
								listeners.remove(entry.getKey());
							}
						}
						Thread.sleep(5000); // 定时触发变更通知
					} catch (Throwable t) { // 防御容错
						t.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void addListener(String key, ICallbackListener listener) {
		listeners.put(key, listener);
		listener.changed(getChanged(key)); // 发送变更通知
	}

	private String getChanged(String key) {
		return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
