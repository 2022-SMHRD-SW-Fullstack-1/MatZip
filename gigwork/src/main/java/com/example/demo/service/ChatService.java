package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.mapper.ChatMapper;
import com.example.demo.model.ChattingContent;
import com.example.demo.model.ChattingRoom;
import com.example.demo.model.Matching;
import com.example.demo.model.MatchingInsert;
import com.example.demo.model.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {
	
	private final ChatMapper chatMapper;

	public List<ChattingRoom> enterChatPage(String nick) {
		return chatMapper.getChattingRoom(nick);
	}
	
	public List<ChattingContent> getChatContent(String roomNum) {
		return chatMapper.getChatContent(roomNum);
	}
	
	public void putChatContent(Map<String, String> data) {
		ChattingContent chatContent = new ChattingContent(0, data.get("talker"), data.get("msg"), null, Integer.parseInt(data.get("cr_seq")));
		chatMapper.putChatContent(chatContent);
	}
	
	public void createChatRoom(Map<String, String> data) {
		ChattingRoom chatroom = new ChattingRoom();
		chatroom.setMem_nick(data.get("mem_nick"));
		chatroom.setPartner_nick(data.get("partner_nick"));
		chatroom.setPost_num(Integer.parseInt(data.get("post_num")));
		chatMapper.createChatRoom(chatroom);
	}
	
	public Post getPostInfo(Map<String, String> data) {
		return chatMapper.getPostInfo(Integer.parseInt(data.get("post_num")));
	}
	
	public void updateCR(String roomnum) {
		chatMapper.updateCR(Integer.parseInt(roomnum));
	}
	
	public void updatePost(String post_num) {
		chatMapper.updatePost(Integer.parseInt(post_num));
	}
	
	public void updatePost2(String post_num) {
		chatMapper.updatePost2(Integer.parseInt(post_num));
	}
	
	public void makeOffer(Map<String, String> data) {
		ChattingRoom chatroom = new ChattingRoom();
		chatroom.setMem_nick(data.get("mem_nick"));
		chatroom.setPartner_nick(data.get("partner_nick"));
		chatroom.setPost_num(Integer.parseInt(data.get("post_num")));
		chatMapper.createChatRoom(chatroom);
		ChattingContent chatContent = new ChattingContent(0, data.get("mem_nick"), data.get("mem_nick")+"님이 "+data.get("post_title")+ " 글에 가격 제안을 보냈어요! ["+data.get("wantPay")+"원]", null, chatMapper.getChatRoom().getCr_seq());
		chatMapper.putChatContent(chatContent);
	}
	
	public void addMatching(Map<String, String> data) {
		MatchingInsert mi = new MatchingInsert();
		mi.setMatch_id(chatMapper.whatIsId(data.get("partner_nick")));
		mi.setPost_num(Integer.parseInt(data.get("post_num")));
		chatMapper.addMatching(mi);
	}
}