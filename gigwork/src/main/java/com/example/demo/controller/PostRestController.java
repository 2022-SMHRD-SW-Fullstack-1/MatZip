package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("post")
@RestController
public class PostRestController {

	private final PostService postService;
	
	Gson gson = new Gson();

	@PostMapping("/create")
	public void createPost(@RequestBody Map<String, Object> data) {

		System.out.println(data);
		postService.createPost(data);
	}

	Gson polistgson = new Gson();
	JsonObject jsonObject = new JsonObject();

	@PostMapping("/postlist")
	public String postlist() {
		JsonArray ja = new JsonArray();
		JsonObject jo = new JsonObject();

		List<Post> postlist = postService.searchPost();

		for (int i = 0; i < postlist.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("post_num", postlist.get(i).getPost_num());
			obj.addProperty("mem_id", postlist.get(i).getMem_id());
			obj.addProperty("post_cate", postlist.get(i).getPost_cate());
			obj.addProperty("post_pay", postlist.get(i).getPost_pay());
			obj.addProperty("post_offer_yn", postlist.get(i).getPost_offer_yn());
			obj.addProperty("worktime_s", postlist.get(i).getWorktime_s());
			obj.addProperty("worktime_e", postlist.get(i).getWorktime_e());
			obj.addProperty("lat", postlist.get(i).getLat());
			obj.addProperty("lng", postlist.get(i).getLng());
			obj.addProperty("urgent", postlist.get(i).getUrgent());
			obj.addProperty("title", postlist.get(i).getTitle());
			obj.addProperty("content", postlist.get(i).getContent());
			obj.addProperty("img_src", postlist.get(i).getImg_src());
			obj.addProperty("reg_date", postlist.get(i).getReg_date());
			obj.addProperty("status", postlist.get(i).getStatus());
			ja.add(obj);
		}
		jo.add("JasonArray", ja);

		return jo.toString();

	}


	// 로그인하는 Id 같이 넣기.
	@PostMapping("/bookmark")
	public List<Post> bookmark(int post_num, String mem_id) {
		return postService.bookmark();
	}
	
	@PostMapping("/getMainPost")
	public String getMainPost() {
		List<Post> postList = postService.getMainPost();
		return gson.toJson(postList);
	}

}
