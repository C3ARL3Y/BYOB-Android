package com.cearleysoftware.byob.ui.fragments.picks

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.constants.DrinkTypes
import com.cearleysoftware.byob.databinding.FragmentCreateDrinkBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.extensions.loadImage
import com.cearleysoftware.byob.models.Drink
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_drink.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreateDrinkFragment: Fragment() {

    private lateinit var binding: FragmentCreateDrinkBinding
    private val createDrinkViewModel by sharedViewModel<CreateDrinkViewModel> ()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    private var drink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drink = arguments?.getParcelable(Constants.DRINK)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = inflater.inflateWithBinding(R.layout.fragment_create_drink, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        nutrientsButton.setOnClickListener { mainViewModel.navigateToNutrientsScreen(drink?.nutrients) }
        stepsButton.setOnClickListener { mainViewModel.navigateToStepsScreen(drink?.steps?: emptyList()) }
        backButton.setOnClickListener { mainViewModel.popBackStack() }
        drinkImageView.setOnClickListener {
            mainViewModel.navigateToImageGallery { path: String, _: Uri ->
                drinkImageView.loadImage(path)
                createDrinkViewModel.drinkData.imageUrl = path
            }
        }
        saveButton.setOnClickListener {
            createDrinkViewModel.saveDrink(
                    drink?.id?: "",
                nameView.text.toString(),
                discriptionView.text.toString(),
                    drink?.type?: DrinkTypes.HOT_DRINKS
            )
        }

        createDrinkViewModel.onDrinkSaved.observe(this, Observer {
            mainViewModel.popBackStack()
        })
        createDrinkViewModel.onDrinkSaveFailed.observe(this, Observer {
            mainViewModel.showAlertDialog("Error", "Could not save drink.")
        })
        binding.drink = drink
    }

    companion object {
        fun newInstance(drink: Drink?): CreateDrinkFragment{
            val bundle = bundleOf(Constants.DRINK to drink)
            val fragment = CreateDrinkFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}