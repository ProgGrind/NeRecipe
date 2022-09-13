package ru.netology.nerecipeapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipeapp.adapter.showCategories
import ru.netology.nerecipeapp.data.Category
import ru.netology.nerecipeapp.databinding.FragmentRecipeFilterBinding
import ru.netology.nerecipeapp.viewModel.RecipeViewModel

class RecipeFilterFragment : Fragment() {


    private val filterViewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecipeFilterBinding.inflate(layoutInflater, container, false).also { binding ->


        with(binding) {
            checkboxEu.text = checkboxEu.context.showCategories(Category.European)
            showFilters(Category.European).also { checkboxEu.isChecked = it }
            checkboxAs.text = checkboxAs.context.showCategories(Category.Asian)
            showFilters(Category.Asian).also { checkboxAs.isChecked = it }
            checkboxPa.text = checkboxPa.context.showCategories(Category.Panasian)
            showFilters(Category.Panasian).also { checkboxPa.isChecked = it }
            checkboxOr.text = checkboxOr.context.showCategories(Category.Oriental)
            showFilters(Category.Oriental).also { checkboxOr.isChecked = it }
            checkboxUs.text = checkboxUs.context.showCategories(Category.American)
            showFilters(Category.American).also { checkboxUs.isChecked = it }
            checkboxRu.text = checkboxRu.context.showCategories(Category.Russian)
            showFilters(Category.Russian).also { checkboxRu.isChecked = it }
            checkboxMe.text = checkboxMe.context.showCategories(Category.Mediterranean)
            showFilters(Category.Mediterranean).also { checkboxMe.isChecked = it }


            if (!filterViewModel.categoriesFilter.contains(Category.Mediterranean))
                binding.checkboxMe.isChecked = false


            binding.setFilter.setOnClickListener {
                onOkButtonClicked(binding)
            }
        }
    }.root


//    override fun onResume() {
//        super.onResume()
//
//        setFragmentResultListener(
//            requestKey = CHECKBOX_KEY
//        ) { requestKey, bundle ->
//            if (requestKey != CHECKBOX_KEY) return@setFragmentResultListener
//            val categories = bundle.getParcelableArrayList<Category>(
//                CHECKBOX_KEY
//            ) ?: return@setFragmentResultListener
//            filterViewModel.showFilters()
//        }
//    }

    private fun showFilters(category: Category): Boolean {
        return filterViewModel.categoriesFilter.contains(category)
    }


//    private fun subFilter (category: Category): Boolean {
//        when filterViewModel.categoriesfilter ()
//
//        if (!filterViewModel.categoriesFilter.contains(category)) {
//            false
//        }
//        false
//    }

    private fun onOkButtonClicked(binding: FragmentRecipeFilterBinding) {
        val categoryList = arrayListOf<Category>()
        var checked = 7
        val nothingIsChecked = 0

        if (binding.checkboxEu.isChecked) {
            categoryList.add(Category.European)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxAs.isChecked) {
            categoryList.add(Category.Asian)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxPa.isChecked) {
            categoryList.add(Category.Panasian)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxOr.isChecked) {
            categoryList.add(Category.Oriental)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxUs.isChecked) {
            categoryList.add(Category.American)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxRu.isChecked) {
            categoryList.add(Category.Russian)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (binding.checkboxMe.isChecked) {
            categoryList.add(Category.Mediterranean)
            filterViewModel.setCategoryFilter = true
        } else {
            checked--
        }
        if (checked == nothingIsChecked) {
            Toast.makeText(activity, "There are no filters set", Toast.LENGTH_LONG).show()
        } else {
            filterViewModel.showRecipesByCategories(categoryList)
            val resultBundle = Bundle(1)
            resultBundle.putParcelableArrayList(CHECKBOX_KEY, categoryList)
            setFragmentResult(CHECKBOX_KEY, resultBundle)
            findNavController().popBackStack()
        }
    }


    companion object {
        const val CHECKBOX_KEY = "checkBoxContent"
    }

}