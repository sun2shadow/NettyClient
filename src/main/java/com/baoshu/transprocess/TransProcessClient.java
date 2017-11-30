package com.baoshu.transprocess;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TransProcessClient {

	static final String host = "127.0.0.1";
	static final int port = 8089;
	private String info ;
	public TransProcessClient(String info) {
		this.info = info;
	}
	public void run() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			 .channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new TransClientHandler(info));
					
				}
				 
			 });
			 
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully();
		}
	}
}
