package com.cearleysoftware.byob.models

//  Copyright © 2019 Cearley Software. All rights reserved.

data class SyrupsData(var syrup: String,
                      var calories: Double = 0.0,
                      var protein: Double = 0.0,
                      var carbs: Double = 0.0,
                      var fats: Double = 0.0,
                      var sugar: Double = 0.0,
                      var count: Int = 0)