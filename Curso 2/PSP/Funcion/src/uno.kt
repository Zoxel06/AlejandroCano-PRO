fun main(args: Array<String>) {
    val nombre = "arp -a"
    val proceso = ProcessBuilder("cmd.exe","/c","arp -a > fichero44.txt")
    proceso.start()
    print("se ha ejecutado $nombre")
}