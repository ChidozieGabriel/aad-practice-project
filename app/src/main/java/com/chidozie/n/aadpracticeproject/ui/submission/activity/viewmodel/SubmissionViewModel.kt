package com.chidozie.n.aadpracticeproject.ui.submission.activity.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.model.GoogleForm
import com.chidozie.n.aadpracticeproject.ui.util.BaseViewModel

class SubmissionViewModel : BaseViewModel() {

    private val showConfirmDialog = MutableLiveData<Unit>()
    val observeShowConfirmDialog: LiveData<Unit>
        get() = showConfirmDialog

    private val showLoading = MutableLiveData<Boolean>()
    val observeShowLoading: LiveData<Boolean>
        get() = showLoading

    private val clearAllFields = MutableLiveData<Unit>()
    val observeClearAllFields: LiveData<Unit>
        get() = clearAllFields

    private val submissionSuccessful = MutableLiveData<Boolean>()
    val observeSubmissionSuccessful: LiveData<Boolean>
        get() = submissionSuccessful

    fun onConfirmClicked(
        firstName: String?,
        lastName: String?,
        emailAddress: String?,
        projectLink: String?
    ) {
        validate(firstName, lastName, emailAddress, projectLink) { form ->
            submit(form)
        }
    }

    fun onSubmitClicked(
        firstName: String?,
        lastName: String?,
        emailAddress: String?,
        projectLink: String?
    ) {
        validate(firstName, lastName, emailAddress, projectLink) {
            showConfirmDialog.postValue(Unit)
        }
    }

    private fun submit(form: GoogleForm) {
        runApi(apiCall = {
            startLoading()

            Repository.submitProject(form)

            submissionSuccessful.postValue(true)
            clearAllFields.postValue(Unit)

        }, onError = {
            submissionSuccessful.postValue(false)

        }, finally = {
            stopLoading()
        })
    }

    private fun validate(
        firstName: String?,
        lastName: String?,
        emailAddress: String?,
        projectLink: String?,
        onValidated: (GoogleForm) -> Unit
    ) {
        if (firstName.isNullOrBlank() ||
            lastName.isNullOrBlank() ||
            emailAddress.isNullOrBlank() ||
            projectLink.isNullOrBlank()
        ) {
            showToast(R.string.all_fields_must_be_filled)
            return
        }

        val isValidEmail = PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches()

        if (isValidEmail.not()) {
            showToast(R.string.email_address_is_not_valid)
            return
        }

        val isValidWebUrl = PatternsCompat.WEB_URL.matcher(projectLink).matches()

        if (isValidWebUrl.not()) {
            showToast(R.string.project_link_is_not_valid)
            return
        }

        val form = GoogleForm(firstName, lastName, emailAddress, projectLink)

        onValidated(form)
    }

    private fun startLoading() {
        showLoading.postValue(true)
    }

    private fun stopLoading() {
        showLoading.postValue(false)
    }
}
