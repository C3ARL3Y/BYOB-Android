package com.cearleysoftware.byob.ui.fragments.picks

import android.app.AlertDialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.extensions.addOnItemLongClick
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.ui.adapters.StepsAdapter
import com.cearleysoftware.byob.ui.viewmodels.CreateDrinkViewModel
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_steps.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StepsFragment: Fragment(){

    private lateinit var stepsAdapter: StepsAdapter
    private lateinit var steps: ArrayList<String>

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private val createDrinkViewModel by sharedViewModel<CreateDrinkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        steps = arguments?.getStringArrayList(Constants.STEPS)?: ArrayList()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.fragment_steps, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        stepsAdapter = StepsAdapter(steps)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(safeActivity)
            adapter = stepsAdapter

            addOnItemLongClick{ position, view ->
                val step = stepsAdapter.getStepForPosition(position)
                showStepMenu(view, step, position)
            }
        }

        doneButton.setOnClickListener {
            createDrinkViewModel.drinkData.steps = steps
            mainViewModel.popBackStack()
        }

        addStepButton.setOnClickListener { showAddStepDialog() }
    }

    private fun showAddStepDialog() {
        val alert = AlertDialog.Builder(activity)
        val edittext = EditText(activity)
        edittext.requestFocus()

        val padding = resources.displayMetrics.dpToPx(15)
        edittext.setPadding(padding,padding,padding,padding)
        alert.setTitle("Add Step")

        alert.setView(edittext)

        alert.setPositiveButton("Ok") { _, _ ->
            val newStep = edittext.text.toString().trim()
            steps.add(newStep.trim())
            stepsAdapter.notifyDataSetChanged()
            createDrinkViewModel.drinkData.steps = steps
        }
        alert.create()
        alert.show()
    }

    private fun showStepMenu(view: View, step: String, position: Int) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.popup_step_edit ->{
                    showEditStepDialog(step, position)
                }

                R.id.popup_step_delete -> removeStep(step)
            }
            true
        }
        popupMenu.inflate(R.menu.menu_popup_step)
        popupMenu.show()
    }

    private fun showEditStepDialog(step: String, position: Int) {
        val alert = AlertDialog.Builder(activity)
        val edittext = EditText(activity)
        edittext.requestFocus()
        val padding = resources.displayMetrics.dpToPx(15)
        edittext.setPadding(padding,padding,padding,padding)
        edittext.setText(step)
        alert.setTitle("Edit step ${position + 1}")

        alert.setView(edittext)

        alert.setPositiveButton("Ok") { _, _ ->
            val newStep = edittext.text.toString().trim()
            steps[position] = newStep.trim()
            stepsAdapter.notifyDataSetChanged()
            createDrinkViewModel.drinkData.steps = steps
        }
        alert.create()
        alert.show()
    }

    private fun removeStep(step: String) {
        steps.remove(step)
        createDrinkViewModel.drinkData.steps = steps
        stepsAdapter.notifyDataSetChanged()
    }

    fun DisplayMetrics.dpToPx(dp: Int): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this ))
    }

    companion object {
        fun newInstance(steps: List<String>): StepsFragment{
            val bundle = bundleOf(Constants.STEPS to steps)
            val fragment = StepsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}