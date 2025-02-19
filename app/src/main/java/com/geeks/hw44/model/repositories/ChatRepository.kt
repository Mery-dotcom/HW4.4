package com.geeks.hw44.model.repositories

import com.geeks.hw44.model.core.RetrofitClient
import com.geeks.hw44.model.models.MessageResponse

class ChatRepository {

    suspend fun getChat(chatId: Int): List<MessageResponse> =
        RetrofitClient.chatService.getChat(chatId)

    suspend fun sendMessage(chatId: Int, message: String, senderId: Int, recieverId: Int): MessageResponse =
        RetrofitClient.chatService.sendMessage(chatId, message, senderId, recieverId)

    suspend fun updateMessage(chatId: Int, messageId: Int, message: String): MessageResponse =
        RetrofitClient.chatService.updateMessage(chatId, messageId, message)

    suspend fun deleteMessage(chatId: Int, messageId: Int): MessageResponse =
        RetrofitClient.chatService.deleteMessage(chatId, messageId)
}