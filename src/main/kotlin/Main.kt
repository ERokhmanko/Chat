fun main() {
    val service = ChatService()
    val user1 = service.addUser("Mish")
    val user2 = service.addUser("Mish2")
    service.addUser("Mish3")

    val getUser = service.getUsersList()
    println(getUser)

    val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")

    val addMess = service.addMess(mess1)
    println(addMess)

    val mess2 = Message(idMessage = 0, userFromId = 1, userToId = 3, idChat = 1, text = "tljfdl")
    service.addMess(mess2)
    service.addMess(Message(idMessage = 0, userFromId = 2, userToId = 3, idChat = 1, text = "tljfdl"))

    service.printChats()

    service.delMess(1, 1)

    println("***************************")
    service.printChats()

    service.editMess(1, 1, "edit")
    println("***************************")
    service.printChats()

   println(service.getMess(1, 3))

    println("***************************")
    service.delChat(1)
    service.printChats()

    println(service.getChatsByUserId(2))
    println(service.getUnreadChatsCount(1))

}