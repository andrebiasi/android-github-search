package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.api.SearchResult
import com.example.githubsearch.api.createGitHubApiService
import com.example.githubsearch.repodetails.RepoDetailsActivity
import com.example.githubsearch.reposlist.ReposAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ReposAdapter { repo ->
            RepoDetailsActivity.startActivity(this, repo)
        }

        val list: RecyclerView = findViewById(R.id.list)
        list.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        val service = createGitHubApiService()
        service.searchRepositories("android").enqueue(object : Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                // handle failure
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val repos = response.body()?.items.orEmpty()
                adapter.submitList(repos)
            }
        })
    }
}