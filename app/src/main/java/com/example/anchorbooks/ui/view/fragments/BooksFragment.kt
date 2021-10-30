package com.example.anchorbooks.ui.view.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anchorbooks.R
import com.example.anchorbooks.databinding.FragmentBooksBinding
import com.example.anchorbooks.ui.view.adapters.BookAdapter
import com.example.anchorbooks.ui.view.interfaces.IComunicateBook
import com.example.anchorbooks.ui.viewModel.BookViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BooksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BooksFragment : Fragment() {
    private var _binding: FragmentBooksBinding?=null
    private  val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var activity: Activity
    lateinit var viewAux:View
    lateinit var iComunicateBook: IComunicateBook
    private lateinit var bookViewModel: BookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        this.bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView()
    {
        this.bookViewModel.books.observe(viewLifecycleOwner, Observer {
            val adapter = BookAdapter(it, requireContext())
            binding.recyclerBooks.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerBooks.adapter=adapter
            adapter.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    var idBook: Int = it.get(binding.recyclerBooks.getChildAdapterPosition(v!!)).id
                    iComunicateBook.viewDetailBook(idBook)
                }
            })
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
        iComunicateBook = activity as IComunicateBook
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BooksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BooksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}