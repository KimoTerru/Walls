package it.kimoterru.walls.ui.search

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.BottomSheetDownloadBinding
import it.kimoterru.walls.databinding.FragmentSearchBinding
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.util.*
import it.kimoterru.walls.util.Status.*
import kotlinx.coroutines.launch

/*This snippet should contain: Fragments - image from search, latest, color range and topics*/
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search),
    WallpaperClickListener.WallpaperClick, WallpaperClickListener.LongClick {

    private val binding: FragmentSearchBinding by viewBinding()
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter by lazy {
        SearchAdapter(this, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        fragmentComponent()
        initRecycler()
    }

    private fun initRecycler() {
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerImageSearch.apply {
            layoutManager = sGrid
            adapter = searchAdapter.apply {
                addLoadStateListener {
                    binding.searchSwipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading
                    binding.errorLayoutSearch.apply {
                        root.isVisible(it.refresh is LoadState.Error)
                        errorMassageView.text = getText(R.string.swipe_to_Refresh)
                        repeatButtonView.gone()
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fragmentComponent() {
        binding.nameSearch.text = args.tittle
        if (args.totalPhotos != 0) {
            binding.sizeSaveWallpaper.text =
                args.totalPhotos.toString() + " " + getText(R.string.wallpapers_available)
            binding.sizeSaveWallpaper.visible()
        } else {
            binding.sizeSaveWallpaper.gone()
        }
        setupSwipeRefreshLayout()
    } //For any garbage associated with onViewCreated

    private fun setupSwipeRefreshLayout() {
        binding.searchSwipeRefreshLayout.apply {
            setColorSchemeResources(R.color.wp_blue)
            setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    searchAdapter.refresh()
                    isRefreshing = false
                }, 350) // Need something that shows the animation of this view
            }
            setProgressBackgroundColorSchemeResource(R.color.my_day_night)
        }
    }

    private fun initObservers() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.getImageSearch(
            args.whichSnippet, args.query, args.query, TopicsOrder.LATEST.query
        ).observe(viewLifecycleOwner) {
            searchAdapter.submitData(lifecycle, it)
        }
    }

    override fun onWallpaperClick(idNetworkPhoto: String, idLocalPhoto: Int, urlImageUser: String) {
        Navigation.findNavController(requireView()).navigate(
            SearchFragmentDirections.actionFragmentSearchToActivityDetailImage(
                idNetworkPhoto, idLocalPhoto, urlImageUser
            )
        )
    }

    override fun onLongClick(fileName: String, linkDownload: String, photo: Photo, lottieAnimationView: LottieAnimationView) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogApplyToTheme)
        val bindingBottomSheet = BottomSheetDownloadBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBottomSheet.root)

        bindingBottomSheet.let {
            it.saveToDownloads.setOnClickListener {
                setupAnimationOnLottie(lottieAnimationView, R.raw.download_animation).playAnimation()
                viewModel.downloadPhoto(fileName, linkDownload)
            }
            it.saveToFavorite.setOnClickListener {
                setupAnimationOnLottie(lottieAnimationView, R.raw.favorite_animation).playAnimation()
                viewModel.saveToFavorite(photo)
            }
        }
        dialog.show()
    }

    private fun setupAnimationOnLottie(lottieAnimationView: LottieAnimationView, animation: Int): LottieAnimationView {
        return lottieAnimationView.apply {
            visible()
            playAnimation()
            setAnimation(animation)
            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    removeAnimatorListener(this)
                    gone()
                }
            })
        }
    }
}