package com.example.anchorbooks.ui.view.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anchorbooks.R
import com.example.anchorbooks.data.db.config.AnchorBooksApp.Companion.prefs
import com.example.anchorbooks.databinding.FragmentBooksBinding
import com.example.anchorbooks.databinding.FragmentDetailBookBinding
import com.example.anchorbooks.ui.view.adapters.BookAdapter
import com.example.anchorbooks.ui.view.interfaces.IComunicateBook
import com.example.anchorbooks.ui.viewModel.BookViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailBookFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDetailBookBinding?=null
    private  val binding get() = _binding!!

    lateinit var activity: Activity
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
        //return inflater.inflate(R.layout.fragment_detail_book, container, false)
        _binding = FragmentDetailBookBinding.inflate(inflater, container, false)
        this.bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        initBookView()
        initSendEmail()
        inicializarBtnMenuPrincipal()
        return binding.root
    }

    private fun initBookView()
    {
        this.bookViewModel.detailBook.observe(viewLifecycleOwner, Observer {
            binding.titleDetail.setText(it.title)
            binding.authorDetail.setText(it.author)
            binding.priceDetail.setText(it.price.toString())
            binding.numPage.setText(it.pages.toString())
            Picasso.get().load(it.imageLink).into(binding.itemImageDetail)
        })
    }

    private fun initSendEmail()
    {
        binding.buyBook.setOnClickListener {
            //get input from EditTexts and save in variables
            val recipient = "yorch5.77@gmail.com"
            val subject = "Consulta por el libro: " + binding.titleDetail.text + " id: " + prefs.idBook
            val message = "Hola\n\n" +
                    "Vi el libro " + binding.titleDetail.text + " de código " + prefs.idBook + " y me gustaría que me contactaran a este correo o al siguiente número _________\n\n" +
                    "Quedo atento."
            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)
        }
    }

    private fun sendEmail(recipient: String, subject: String, message: String)
    {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }

    }

    private fun inicializarBtnMenuPrincipal()
    {
        binding.btnAtras.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                iComunicateBook.viewBooks()
            }
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
         * @return A new instance of fragment DetailBookFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailBookFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}