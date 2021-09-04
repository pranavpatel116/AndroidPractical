package com.dev.practical.model

class DrawerMenuModel {
    var id: Int = 0
    var drawerName: String = ""
    var drawerIcon: Int = 0

    constructor()

    constructor(id: Int, drawerName: String, drawerIcon: Int) {
        this.id = id
        this.drawerName = drawerName
        this.drawerIcon = drawerIcon
    }


}