nodo = {
    "valor": any,
    "izq": None,
    "der": None
}

def crearNodo(valor):
    return {
        "valor": valor,
        "izq":  None,
        "der": None
    }

def insertar(raiz, valor):
    if (raiz is None):
        return crearNodo(valor)
    
    if valor < raiz["valor"]:
        raiz["izq"] = insertar(raiz["izq"], valor)
    
    else:
        raiz["der"] = insertar(raiz["der"], valor)

    return raiz



def preorder(raiz):
    print(raiz["valor"], end="")
    preorder(raiz["izq"])
    preorder(raiz["der"])


def postorder(raiz):
    postorder(raiz["izq"])
    postorder(raiz["der"])
    print(raiz["valor"], end="")


def inorder(raiz):
    inorder(raiz["izq"])
    print(raiz["valor"], end="")
    inorder(raiz["der"])