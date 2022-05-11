package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.notification.NotificationCreateReq;
import a204.ssayeon.api.request.notification.NotificationUpdateReq;
import a204.ssayeon.api.response.notification.NotificationShowListRes;
import a204.ssayeon.api.service.NotificationService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.PaginationResponseBody;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdvancedResponseBody<String> create(@RequestBody NotificationCreateReq notificationCreateReq){
        notificationService.create(notificationCreateReq);
        return AdvancedResponseBody.of(Status.CREATED);
    }

    @PatchMapping("/{id}")
    public AdvancedResponseBody<String> update(@PathVariable Long id, @RequestBody NotificationUpdateReq notificationUpdateReq){
        notificationService.update(id, notificationUpdateReq);
        return AdvancedResponseBody.of(Status.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public AdvancedResponseBody<String> delete(@PathVariable Long id){
        notificationService.delete(id);
        return AdvancedResponseBody.of(Status.OK);
    }

    @GetMapping
    public AdvancedResponseBody<List<NotificationShowListRes>> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=10) Pageable pageable){ //페이지네이션
        Page<Notification> notificationPage = notificationService.showList(pageable);

        Pagination pagination = Pagination.getPagination(notificationPage);
        List<NotificationShowListRes> notificationList = new ArrayList<>();

        notificationPage.forEach((notification) -> {
            notificationList.add(NotificationShowListRes.builder().id(notification.getId()).title(notification.getTitle()).views(notification.getViews()).createdAt(notification.getCreatedAt()).updatedAt(notification.getUpdatedAt()).build());
        });
        return PaginationResponseBody.of(Status.OK, notificationList, pagination);
    }

    @GetMapping("/{id}")
    public AdvancedResponseBody<Notification> showDetail(@PathVariable Long id){
        return AdvancedResponseBody.of(Status.OK, notificationService.showDetail(id));
    }
}
