package com.geeks.hw44.view

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.geeks.hw44.databinding.FragmentChatBinding
import com.geeks.hw44.model.models.MessageResponse
import com.geeks.hw44.view.adapters.MessageAdapters

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private val adapter = MessageAdapters(
        clickListener = { message -> showUpdateDialog(message) },
        longClickListener = { message -> onLongClick(message) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        init()

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.submitList(messages)
        }
    }

    private fun setupListeners() {
        binding.apply {
            button.setOnClickListener {
                viewModel.sendMessage(
                    chatId = 1,
                    message = editText.text.toString(),
                    senderId = 1,
                    recieverId = 2
                )
            }

            button.setOnClickListener{
                val messageText = editText.text.toString()
                if (messageText.isNotEmpty()){
                    viewModel.sendMessage(1, messageText, 2, 3)
                    editText.text.clear()
                }
            }
        }
    }

    private fun init() {
        binding.recyclerView.adapter = adapter
        viewModel.getChat(10)
    }

    private fun showUpdateDialog(message: MessageResponse) {
        val editText = EditText(requireContext()).apply {
            setText(message.message)
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Update Message")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                viewModel.updateMessage(message.chatId!!, message.id!!, editText.text.toString())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun onLongClick(message: MessageResponse) {
        viewModel.deleteMessage(message.chatId!!, message.id!!)
    }
}