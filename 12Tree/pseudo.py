def evaluate(node):
  if node.isLeaf():
    return node.toNumber()
  return node.evalSign(evaluate(node.left()), evaluate(node.right()))
  """
  evalSign(a, b)
    if node.getValue == "+":
      return a + b
  ....
  """
