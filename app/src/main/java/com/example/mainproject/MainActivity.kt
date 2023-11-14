package com.example.mainproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mainproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //액션바 컨트롤
        binding = ActivityMainBinding.inflate(layoutInflater)

        val appBarConfiguration = AppBarConfiguration( //탑레벨 지정->이 화면들에서는 업버튼X
            setOf(R.id.myAssetFragment, R.id.assetPlanListFragment, R.id.savingPlanListFragment,
                R.id.entryFragment, R.id.settingsFragment)
        )

        val navController = binding.frgNav.getFragment<NavHostFragment>().navController//내브컨트롤러 가져오기
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean { //업버튼 설정
        val navController=binding.frgNav.getFragment<NavHostFragment>().navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}