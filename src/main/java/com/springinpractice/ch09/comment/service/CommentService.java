package com.springinpractice.ch09.comment.service;

import com.springinpractice.ch09.comment.model.Comment;

public interface CommentService {

	
	void postComment(Comment comment, final PostCommentCallback callback);
	
}
