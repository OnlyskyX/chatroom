package com.onlysky.chatroom.controller.service;

import com.onlysky.chatroom.bean.CommonResult;

public interface ChatRoomService {

    CommonResult joinRoom(String roomId, String userId);

    CommonResult leaveRoom(String roomId,String userId);

}
