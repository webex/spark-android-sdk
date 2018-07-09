package com.ciscospark.androidsdk.message;

/**
 *
 */
public interface MessageObserver {

    /**
     *
     */
    abstract class MessageEvent {
    }

    /**
     *
     */
    class MessageArrived extends  MessageEvent {
        private Message message;

        public MessageArrived(Message message) {
            this.message = message;
        }
        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    /**
     *
     */
    class MessageDeleted extends MessageEvent {
        private String messageId;

        public MessageDeleted(String messageId) {
            this.messageId = messageId;
        }

        public String getMessageId() {
            return messageId;
        }
    }

    /**
     * Call back when message arrived.
     * @param event     Message event
     */
    void onEvent(MessageEvent event);
}
