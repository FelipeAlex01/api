package br.com.up.project.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@javax.ejb.ApplicationException
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> listMessages;

	public ApplicationException(String message) {
		super(message);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public ApplicationException(Collection<String> messages) {
		super(messages == null ? null : messages.toString());
		this.listMessages = new ArrayList<>(messages);
	}

	public List<String> getListMessages() {
		this.initializeListMessage();
		return Collections.unmodifiableList(this.listMessages);
	}

	private void initializeListMessage() {
		if (this.listMessages == null) {
			this.listMessages = new ArrayList<String>();
		}
	}
}
