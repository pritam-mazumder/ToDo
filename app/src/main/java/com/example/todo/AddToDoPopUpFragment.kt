package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todo.databinding.FragmentAddToDoPopUpBinding
import com.example.todo.utils.ToDoData
import com.google.android.material.textfield.TextInputEditText

class AddToDoPopUpFragment : DialogFragment() {
    private lateinit var binding: FragmentAddToDoPopUpBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData: ToDoData? = null

    fun setListener(listener: DialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "AddToDoPopUpFragment"

        @JvmStatic
        fun newInstance(taskId: String, task: String) = AddToDoPopUpFragment().apply {
            arguments = Bundle().apply {
                putString("taskId", taskId)
                putString("task", task)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddToDoPopUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            toDoData = ToDoData(
                arguments?.getString("taskId").toString(),
                arguments?.getString("task").toString()
            )
            binding.todoEt.setText(toDoData?.task)
        }
        registerEvents()
    }

    private fun registerEvents() {
        binding.todoNextBtn.setOnClickListener {
            val todoTask = binding.todoEt.text.toString()
            if (todoTask.isNotEmpty()) {
                if (toDoData == null) {
                    listener.onSaveTask(todoTask, binding.todoEt)
                } else {
                    toDoData?.task = todoTask
                    listener.onUpdateTask(toDoData!!, binding.todoEt)
                }
            } else {
                Toast.makeText(context, "Field is empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelTask.setOnClickListener {
            dismiss()
        }
    }

    interface DialogNextBtnClickListener {
        fun onSaveTask(todo: String, todoEt: TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData, todoEt: TextInputEditText)
    }

}