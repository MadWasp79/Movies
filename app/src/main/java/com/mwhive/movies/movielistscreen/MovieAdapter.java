package com.mwhive.movies.movielistscreen;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mwhive.movies.R;
import com.mwhive.movies.model.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadWasp79 on 01-Aug-18.
 *
 *  * У котлина проблема с загрузкой имиджей глайдом. Ну, не то что б проблема, но нужно дополнительно описывать scaling,
 * потому в качестве полноценной замены переписал адаптер на java. А вообще хороший вопрос, нужно будет еще покурить его.
 *
 */

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

  private final List<Movie> data = new ArrayList<>();

  public MovieAdapter() {
    setHasStableIds(false);
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_card, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  @Override
  public long getItemId(int position) {
    return data.get(position).getId();
  }

  void setData(List<Movie> movies) {
    data.addAll(movies);
    notifyDataSetChanged();
  }

  public class MovieViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvCount;
    ImageView ivPoster;

    private Movie movie;
    String baseImageUrl = "http://image.tmdb.org/t/p/w500/";

    public MovieViewHolder(View itemView) {
      super(itemView);
      tvTitle = (TextView) itemView.findViewById(R.id.movie_title);
      tvCount = (TextView) itemView.findViewById(R.id.movie_mark);
      ivPoster = (ImageView) itemView.findViewById(R.id.poster_image);

    }

    void bind(Movie movie) {
      this.movie = movie;
      tvTitle.setText(movie.getTitle());
      tvCount.setText(movie.getVoteAverage()+ " (" + movie.getVoteCount()+" votes)");
      Glide.with(ivPoster.getContext()).load(baseImageUrl + movie.getPosterPath()).into(ivPoster);
    }
  }
}
