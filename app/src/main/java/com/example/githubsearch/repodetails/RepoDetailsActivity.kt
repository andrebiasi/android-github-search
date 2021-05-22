package com.example.githubsearch.repodetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.githubsearch.R
import com.example.githubsearch.models.Repo

class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        val name = intent.getStringExtra(KEY_NAME)
        val ownerName = intent.getStringExtra(KEY_OWNER_NAME)

        val nameText: TextView = findViewById(R.id.name)
        val ownerNameText: TextView = findViewById(R.id.ownerName)

        nameText.text = name
        ownerNameText.text = "@$ownerName"
    }

    companion object {

        private const val KEY_NAME = "key_name"
        private const val KEY_OWNER_NAME = "key_owner_name"

        fun startActivity(context: Context, repo: Repo) {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.run {
                putExtra(KEY_NAME, repo.name)
                putExtra(KEY_OWNER_NAME, repo.owner.login)
            }.also {
                context.startActivity(it)
            }
        }
    }
}