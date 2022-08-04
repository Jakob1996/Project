package com.example.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.app.adapter.ItemAdapter
import com.example.app.data.Item
import com.example.app.databinding.FragmentListBinding
import com.example.app.listener.OnItemClickListener
import com.example.app.ui.activity.SecondActivity
import com.example.app.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentListBinding? = null

    private lateinit var itemAdapter: ItemAdapter

    private val binding get() = _binding!!

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        CoroutineScope(Dispatchers.Main).launch {
            mainViewModel.let {
                val firstItems = it.getFirstItems()
                val secondItems = it.getSecondItems()

                firstItems.list.forEach {
                    mainViewModel.repository.saveItem(Item(login = it.screenName, id = it.id))
                }

                secondItems.forEach {
                    it.run {
                        mainViewModel.repository.saveItem(
                            Item(
                                avatar_url,
                                events_url,
                                followers_url,
                                following_url,
                                gists_url,
                                gravatar_id,
                                html_url,
                                id.toString(),
                                login,
                                node_id,
                                organizations_url,
                                received_events_url,
                                repos_url,
                                site_admin,
                                starred_url,
                                subscriptions_url,
                                type,
                                url
                            )
                        )
                    }
                }

                mainViewModel.getAllItems().observe(viewLifecycleOwner) {

                    itemAdapter = ItemAdapter(it, this@ListFragment)

                    binding.fragmentListRv.adapter = itemAdapter
                }
            }
        }
    }

    fun initRecyclerView() {
        val lm = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        binding.fragmentListRv.layoutManager = lm

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onItemClick(item: Item, position: Int) {
        val intent = Intent(activity, SecondActivity::class.java)
        saveItemData(item, intent)
        startActivity(intent)
    }

    private fun saveItemData(item: Item, intent: Intent) {

        item.run {
            val username = login
            val image = avatar_url
            val url = url

            intent.run {
                putExtra("username", username)
                putExtra("image", image)
                putExtra("url", url)
            }
        }
    }
}