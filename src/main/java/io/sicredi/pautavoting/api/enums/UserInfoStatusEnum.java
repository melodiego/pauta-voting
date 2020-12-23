package io.sicredi.pautavoting.api.enums;

public enum UserInfoStatusEnum {

    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private String status;

    private UserInfoStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}