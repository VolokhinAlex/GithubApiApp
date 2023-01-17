package com.volokhinaleksey.popularlibrariesandroid

class MainPresenter(
    private val counterModel: CountersModel,
    private val view: MainView
) {
    fun counterClick(id: Int) = when (id) {
        0 -> {
            val nextValue = counterModel.nextCount(0)
            view.setButton1Text(nextValue.toString())
        }
        1 -> {
            val nextValue = counterModel.nextCount(1)
            view.setButton2Text(nextValue.toString())
        }
        2 -> {
            val nextValue = counterModel.nextCount(2)
            view.setButton3Text(nextValue.toString())
        }
        else -> {}
    }
}