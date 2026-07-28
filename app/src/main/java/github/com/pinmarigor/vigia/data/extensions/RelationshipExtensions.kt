package github.com.pinmarigor.vigia.data.extensions

import github.com.pinmarigor.vigia.data.model.Relationship

fun String.toRelationship(): Relationship =
    Relationship.entries.firstOrNull {
        it.name == this
    } ?: Relationship.OUTRO