package com.springboot.boxview;
      import java.util.ArrayList;
      import java.util.HashMap;
      import java.util.List;
      import java.util.Map;

        import org.springframework.stereotype.Controller;
      import org.springframework.validation.BindingResult;
      import org.springframework.web.bind.annotation.GetMapping;
      import org.springframework.web.bind.annotation.PostMapping;
      import org.springframework.web.bind.annotation.RequestParam;
      import org.springframework.web.servlet.ModelAndView;
      import org.springframework.web.servlet.view.RedirectView;

      import javax.validation.Valid;

@Controller
public class BoxviewController {
    List<Movie> movies = new ArrayList<Movie>();

    @GetMapping("/movieForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
        Movie movie = findMovieById(id);
        String viewName = "movieForm";
        Map<String,Object> model = new HashMap<String,Object>();
        if (movie == null) {
            model.put("movie", new Movie());

        } else {
            model.put("movie", movie);
        }
      return new ModelAndView(viewName,model);
    }

    private Movie findMovieById(Integer id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }
    @PostMapping("/movieForm")
    public ModelAndView submitWatchlistItemForm(@Valid Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("movieForm");
        }
        Movie existingMovie= findMovieById(movie.getId());
        if(existingMovie==null){
            if (itemAlreadyExists(movie.getTitle())) {
                bindingResult.rejectValue("title", "", "This movie is already on your watchlist");
                return new ModelAndView("movieForm");
            }

            movie.setId(movie.index++);
            movies.add(movie);
        }else {
            existingMovie.setComment(movie.getComment());
            existingMovie.setPriority(movie.getPriority());
            existingMovie.setRating(movie.getRating());
            existingMovie.setTitle(movie.getTitle());
        }
      RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }
    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {

        String viewName = "watchlist";

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("movies", movies);
        model.put("numberOfMovies", movies.size());


        return new ModelAndView(viewName , model);
    }
    private boolean itemAlreadyExists(String title) {

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }


}
