package com.uteam.person_movie.Presentation.Controller;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.CreatePersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.PatchPersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.CreatePersonResponseDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PatchPersonResponseDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IAddMovieToPersonUseCase;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IGetPersonMoviesUseCase;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IRemoveMovieFromPersonUseCase;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.*;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final ICreatePersonUseCase createPersonUseCase;
    private final IDeletePersonUseCase deletePersonUseCase;
    private final IPatchPersonUseCase patchPersonUseCase;
    private final IGetAllPersonUseCase getAllPersonUseCase;
    private final IGetPersonByIdUseCase getPersonByIdUseCase;
    private final IGetPersonByNameUseCase getPersonByNameUseCase;

    private final IGetPersonMoviesUseCase getPersonMoviesUserCase;
    private final IAddMovieToPersonUseCase addMovieToPersonUseCase;
    private final IRemoveMovieFromPersonUseCase removeMovieFromPersonUseCase;


    @GetMapping("/{id}")
    public Result<PersonResponseDto> getPersonById(@PathVariable("id") int id) {
        return getPersonByIdUseCase.execute(id);
    }

    @GetMapping("/{personId}/movies")
    public Result<List<MovieResponseDto>> getPersonB(@PathVariable("personId") int personId) {
        return getPersonMoviesUserCase.execute(personId);
    }

    @PostMapping("/{personId}/movies")
    public Result<MovieResponseDto> addMovieToPerson(@PathVariable("personId") int personId, @Valid @RequestBody CreateMovieRequestDto requestDto) {
        return addMovieToPersonUseCase.execute(personId,requestDto);
    }

    @DeleteMapping("/{personId}/movies/{movieTitle}")
    public Result<Object> addMovieToPerson(
            @PathVariable("personId") int personId,
            @PathVariable("movieTitle") String movieTitle) {
        return removeMovieFromPersonUseCase.execute(personId, movieTitle);
    }

    @GetMapping("/search")
    public Result<List<PersonResponseDto>> getPersonByName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName
    ) {
        return getPersonByNameUseCase.execute(firstName, lastName);
    }

    @GetMapping
    public Result<List<PersonResponseDto>> getAllPerson() {
        return getAllPersonUseCase.execute();
    }

    @PostMapping
    public Result<CreatePersonResponseDto> createPerson(@Valid  @RequestBody CreatePersonRequestDto requestDto) {
        return createPersonUseCase.execute(requestDto);
    }

    @PatchMapping("/{id}")
    public Result<PatchPersonResponseDto> patchPerson(
            @PathVariable("id") int id,
            @Valid  @RequestBody PatchPersonRequestDto requestDto
    ) {
        return patchPersonUseCase.execute(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Result<Object> deletePerson(@PathVariable("id") int id) {
        return deletePersonUseCase.execute(id);
    }
}
