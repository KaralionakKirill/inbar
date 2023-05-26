package by.inbar.backend.specification

import by.inbar.backend.dto.filter.LazyLoadEvent
import by.inbar.backend.model.user.User

class UserSpecification(
    lazyLoadEvent: LazyLoadEvent
) : AbstractSpecification<User>(lazyLoadEvent)
