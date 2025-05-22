package com.example.crickarina.Data

import com.example.crickarina.Model.BestSeller2

import com.example.crickarina.Model.kookabu
import com.example.crickarina.R

class BestSeller22 {
    fun loadBestNavigationIterm ():List<BestSeller2>{
        return listOf<BestSeller2>(
            BestSeller2(
                R.drawable.kahuna51,
                R.string.bat1,
                R.string.price_bat2,
            ),
            BestSeller2(
                R.drawable.aura,
                R.string.bat2,
                R.string.price_bat2,
            ),
            BestSeller2(
                R.drawable.beast21,
                R.string.bat3,
                R.string.price_bat3,
            ),
            BestSeller2(
                R.drawable.beast31,
                R.string.bat4,
                R.string.price_bat4,
            )
        )
    }
}
