package com.mwhive.movies.movielistscreen

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mwhive.movies.R
import com.mwhive.movies.model.Movie


/**
 * Created by MadWasp79 on 31-Jul-18.
 *
 * У котлина проблема с загрузкой имиджей глайдом. Ну, не то что б проблема, но нужно дополнительно описывать scaling,
 * потому в качестве полноценной замены переписал адаптер на java. А вообще хороший вопрос, нужно будет еще покурить его.
 *
 */

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    val data = mutableListOf<Movie>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_card, parent, false)

        return MoviesViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    fun setData(movies: List<Movie>){
        data.addAll(movies)
        notifyDataSetChanged()
    }

    class MoviesViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        var posterImageView : ImageView = itemView.findViewById<ImageView>(R.id.poster_image)
        var textBackground : LinearLayout = itemView.findViewById<LinearLayout>(R.id.movie_text_background)
        var movieTitle: TextView = itemView.findViewById<TextView>(R.id.movie_title)
        var movieMark : TextView = itemView.findViewById<TextView>(R.id.movie_mark)

        private lateinit var movie: Movie

        private val baseImageUrl = "http://image.tmdb.org/t/p/w342/"

        fun bind(movie: Movie) {
            this.movie = movie
            val votesLine = "${movie.voteAverage}/10 (${movie.voteCount} votes)"
            val imageUrl = baseImageUrl+movie.posterPath

            movieTitle.text = movie.title
            movieMark.text = votesLine
            Glide.with(posterImageView.context)
                    .load(imageUrl)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.pl_holder).fitCenter())
                    .into(posterImageView)


            //todo imagecolor analize and change
        }

    }
}


