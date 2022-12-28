package com.example.paging3mvvmhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3mvvmhilt.paging.LoaderAdapter
import com.example.paging3mvvmhilt.paging.QuotePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var  adapter: QuotePagingAdapter

    private  val viewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = QuotePagingAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        viewModel.getQuotesWithContext()
        viewModel.list.observe(this ,{

//            adapter.submitData( lifecycle ,it)

        })


        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.quoteList.collect{
                   if(it.isLoading){
                       //show progress bar
                       Toast.makeText(this@MainActivity,"Loading state ", Toast.LENGTH_LONG).show()
                   }
                    if (it.error.isNotBlank()){
                        Toast.makeText(this@MainActivity,"${it.error}", Toast.LENGTH_LONG).show()
                    }
                    it.data?.let {
                        adapter.submitData(it)
                    }
                }
            }
        }
    }

}