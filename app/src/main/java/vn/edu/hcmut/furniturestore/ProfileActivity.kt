package vn.edu.hcmut.furniturestore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import vn.edu.hcmut.furniturestore.viewmodel.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var ivUserPhoto: ImageView
    private lateinit var tvDisplayName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnSignOut: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        bottomNavView = findViewById(R.id.bottom_nav_view)
        setupBottomNavigation()
        // Initialize views
        ivUserPhoto = findViewById(R.id.ivUserPhoto)
        tvDisplayName = findViewById(R.id.tvDisplayName)
        tvEmail = findViewById(R.id.tvEmail)
        btnSignOut = findViewById(R.id.btnSignOut)
        progressBar = findViewById(R.id.progressBar)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Set up observers
        viewModel.user.observe(this) { user ->
            if (user != null) {
                tvDisplayName.text = user.displayName ?: "No Name"
                tvEmail.text = user.email ?: "No Email"

                if (!user.photoUrl.isNullOrEmpty()) {
                    Glide.with(this)
                        .load(user.photoUrl)
                        .circleCrop()
                        .placeholder(R.drawable.ic_user)
                        .into(ivUserPhoto)
                } else {
                    ivUserPhoto.setImageResource(R.drawable.ic_user)
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.signOutResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                },
                onFailure = { exception ->
                    Toast.makeText(this, "Sign out failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // Set up click listeners
        btnSignOut.setOnClickListener {
            viewModel.signOut()
        }

        // Load user profile
        viewModel.loadUserProfile()
    }
    private fun setupBottomNavigation() {
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_user -> {
                    // Already in Profile screen
                    true
                }
                R.id.nav_ar -> {
                    try {
                        val intent = Intent(this, ARScreenActivity::class.java)
                        startActivity(intent)
                        true
                    } catch (e: Exception) {
                        Toast.makeText(this, "Error launching AR: ${e.message}", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
                else -> false
            }
        }

        // Highlight the profile item
        bottomNavView.selectedItemId = R.id.nav_user
    }
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}