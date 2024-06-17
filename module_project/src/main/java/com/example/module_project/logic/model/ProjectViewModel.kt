package com.example.module_project.logic.model

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.gson.ProjectTreeBean
import com.example.commonlibary.util.LogUtil
import com.example.module_project.logic.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor() : BaseViewModel() {
    private val TAG = "ProjectViewModel"
    @Inject
    lateinit var mRepository: ProjectRepository
    val mProjectTreeLiveData : MutableLiveData<List<ProjectTreeBean>> = MutableLiveData()
    fun getProjectTreeByCoroutine(){
        CoroutineScope(Dispatchers.Main).launch {
            LogUtil.d(TAG,"getProjectTree")
            val projectTreeBeanList = mRepository.getProjectTree().data
            mProjectTreeLiveData.value = projectTreeBeanList
        }
    }
}