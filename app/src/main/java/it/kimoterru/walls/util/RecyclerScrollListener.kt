package it.kimoterru.walls.util

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class RecyclerScrollListener constructor() :
    RecyclerView.OnScrollListener() {

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: GridLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager: StaggeredGridLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager: LinearLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }

    /*
     Метод получает обратный вызов, когда пользователь прокручивает список поиска
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = mLayoutManager.childCount
        val totalItemCount = mLayoutManager.itemCount
        var firstVisibleItemPosition = 0

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val firstVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(null)
                // получить максимальный элемент в списке
                firstVisibleItemPosition = firstVisibleItemPositions[0]
            }
            is GridLayoutManager -> {
                firstVisibleItemPosition =
                    (mLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                firstVisibleItemPosition =
                    (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }
        }
        if (!isLoading) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                Log.i(TAG, "Загрузка большего количества товаров")
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    //abstract val isLastPage: Boolean
    abstract val isLoading: Boolean

    companion object {
        private val TAG = RecyclerScrollListener::class.java.simpleName
    }
}