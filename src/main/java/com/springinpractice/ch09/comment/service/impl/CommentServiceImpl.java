package com.springinpractice.ch09.comment.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springinpractice.ch09.comment.model.Comment;
import com.springinpractice.ch09.comment.service.CommentService;
import com.springinpractice.ch09.comment.service.PostCommentCallback;
import com.springinpractice.ch09.comment.service.TextFilter;
import com.springinpractice.web.WebUtils;

@Service
public class CommentServiceImpl implements CommentService{

	@Inject private TextFilter textFilter;
	@Inject private CommentMailSender mailSender;
	
	public TextFilter getTextFilter() { return textFilter; }
	
	public void setTextFilter(TextFilter filter) {
		
		this.textFilter = filter;
		
	}
	
	public void postComment(
			final Comment comment, final PostCommentCallback callback) {
		
		prepareComment(comment);
		callback.post(comment);
		mailSender.sendNotificationEmail(comment);
		
	} // end postComment()
	
	private void prepareComment(final Comment comment) {
		
		comment.setWeb(WebUtils.cleanupWebUrl(comment.getWeb()));
		comment.setDateCreated(new Date());
		comment.setText(textFilter.filter(comment.getText()));
		
	} // end prepareComment()
	
} // end CommentServiceImpl class
