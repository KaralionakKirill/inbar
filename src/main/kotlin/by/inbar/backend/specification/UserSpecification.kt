package by.inbar.backend.specification

import by.inbar.backend.dto.filter.Filter
import by.inbar.backend.model.user.Role
import by.inbar.backend.model.user.User

class UserSpecification(
    filter: Filter
) : AbstractSpecification<User>(filter){
    override fun setup(builder: PredicateBuilder<User>) = with(builder) {
        addEqualSupport(User::role.name) { Role.valueOf(it) }
    }
}
