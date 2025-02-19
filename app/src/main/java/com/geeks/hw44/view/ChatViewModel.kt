package com.geeks.hw44.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.hw44.model.models.MessageResponse
import com.geeks.hw44.model.repositories.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val _messages = MutableLiveData<List<MessageResponse>>()
    val messages: LiveData<List<MessageResponse>> get() = _messages

    fun getChat(chatId: Int){
        viewModelScope.launch {
            try {
                val response = repository.getChat(chatId)
                _messages.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ololo", "getChat: ${e.message}", )
            }
        }
    }

    fun sendMessage(chatId: Int, message: String, senderId: Int, recieverId: Int){
        viewModelScope.launch {
            try {
               val newMessage = repository.sendMessage(chatId, message, senderId, recieverId)
                val updateList = _messages.value?.toMutableList() ?: mutableListOf()
                updateList.add(newMessage)
                _messages.postValue(updateList)
                refreshChat(chatId)
                Log.e("ololo", "sendMessage", )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ololo", "sendMessage: ${e.message}", )
            }
        }
    }

    fun updateMessage(chatId: Int, messageId: Int, message: String){
        viewModelScope.launch {
            try {
                repository.updateMessage(chatId, messageId, message)
                refreshChat(chatId)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ololo", "updateMessage: ${e.message}", )
            }
        }
    }

    fun deleteMessage(chatId: Int, messageId: Int){
        viewModelScope.launch {
            try {
                repository.deleteMessage(chatId, messageId)
                refreshChat(chatId)
            } catch (e: Exception) {
                Log.e("ololo", "deleteMessage: ${e.message}", )
            }
        }
    }

    fun refreshChat(chatId: Int){
        viewModelScope.launch {
            try {
             val response = repository.getChat(chatId)
                _messages.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ololo", "refreshChat: ${e.message}", )
            }
        }
    }
}