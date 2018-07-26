package com.fzz.cloud.fzzcloudlockingSystem.mapper.room;

import org.apache.ibatis.annotations.Param;

public interface RoomMapper {
	
	void updateRoomByLockId(@Param(value = "roomId") String roomId, @Param(value = "lockId") String lockId);
}