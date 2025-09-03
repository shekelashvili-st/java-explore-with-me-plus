package ru.practicum.main.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.comment.dto.CommentDto;
import ru.practicum.main.comment.dto.NewCommentDto;
import ru.practicum.main.comment.dto.UpdateCommentDto;
import ru.practicum.main.comment.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Slf4j
public class AdminCommentController {

    private final CommentService commentService;

    @PostMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createByAdmin(@PathVariable Long eventId,
                                  @RequestParam Long authorId,
                                  @Valid @RequestBody NewCommentDto body) {
        log.info("ADMIN create comment for event={} by author={}", eventId, authorId);
        return commentService.createCommentByAdmin(authorId, eventId, body);
    }

    @GetMapping
    public List<CommentDto> searchByAdmin(@RequestParam(required = false) List<Long> authors,
                                        @RequestParam(required = false) List<String> states,
                                        @RequestParam(required = false) List<Long> events,
                                        @RequestParam(required = false) String rangeStart,
                                        @RequestParam(required = false) String rangeEnd,
                                        @RequestParam(defaultValue = "0") Integer from,
                                        @RequestParam(defaultValue = "10") Integer size) {
        log.info("ADMIN search comments authors={} states={} events={}", authors, states, events);
        return commentService.searchCommentByAdmin(authors, states, events, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateByAdmin(@PathVariable Long commentId, @Valid @RequestBody UpdateCommentDto body) {
        log.info("ADMIN update comment={}", commentId);
        return commentService.updateCommentByAdmin(commentId, body);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAdmin(@PathVariable Long commentId) {
        log.info("ADMIN delete comment={}", commentId);
        commentService.deleteCommentByAdmin(commentId);
    }
}