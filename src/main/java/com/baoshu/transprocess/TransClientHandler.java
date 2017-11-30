package com.baoshu.transprocess;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TransClientHandler extends ChannelInboundHandlerAdapter {
	
	private  ByteBuf firstMessage;
	
	public TransClientHandler(String transInfo) {
		firstMessage=Unpooled.buffer();  
		try {
			firstMessage.writeBytes(transInfo.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		String reciveved = getMessage(buf);
		System.out.println("客户端收到服务器数据:" + reciveved); 
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private String getMessage(ByteBuf buf) {  
		  
        byte[] con = new byte[buf.readableBytes()];  
        buf.readBytes(con);  
        try {  
            return new String(con, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
	
}

