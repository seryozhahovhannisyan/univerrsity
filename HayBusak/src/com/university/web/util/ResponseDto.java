package com.university.web.util;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class ResponseDto implements Serializable {

    private ResponseStatus status;

    private List<String> messages;

    public ResponseDto() {
    }

    public void addMessage(String message) {
        if(messages == null){
            messages = new ArrayList<String>();
        }
        messages.add(message);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", messages=" + messages +
                '}';
    }
}
