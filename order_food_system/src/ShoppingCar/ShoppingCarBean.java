package ShoppingCar;

public class ShoppingCart implements Serializable {
    <p>购物车总价:￥${cart.totalPrice}</p>

<table>
  <tr>
    <th>菜品名称</th>
    <th>数量</th>
    <th>小计</th>
    <th>操作</th>
  </tr>

  <c:forEach items="${cart.cartItemList}" var="item">
    <tr>
      <td>${item.dish.name}</td>
      <td>
        <form action="updateCart.do" method="post" style="display:inline;">
          <input type="hidden" name="dishId" value="${item.dish.id}">
          <input type="number" name="count" value="${item.count}" min="1" max="99" style="width:50px;">
          <input type="submit" value="修改">
        </form>
      </td>
      <td>￥${item.subTotal}</td>
      <td>
        <form action="removeFromCart.do" method="post" style="display:inline;">
          <input type="hidden" name="dishId" value="${item.dish.id}">
          <input type="submit" value="删除">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>


    private static final long serialVersionUID = 1L;

    private Map<Integer, CartItem> cartItems = new HashMap<>();

    // 添加购物项
    public void addCartItem(CartItem item) {
        if(cartItems.containsKey(item.getDish().getId())) {
            CartItem oldItem = cartItems.get(item.getDish().getId());
            oldItem.setCount(oldItem.getCount() + item.getCount());
        } else {
            cartItems.put(item.getDish().getId(), item);
        }
    }

    // 移除购物项
    public void removeCartItem(int dishId) {
        if(cartItems.containsKey(dishId)) {
            cartItems.remove(dishId);
        }
    }

    // 修改购物项数量
    public void updateCartItem(int dishId, int count) {
        if(cartItems.containsKey(dishId)) {
            CartItem item = cartItems.get(dishId);
            item.setCount(count);
        }
    }

    // 清空购物车
    public void clearCart() {
        cartItems.clear();
    }

    // 获取购物车列表
    public List<CartItem> getCartItemList() {
        return new ArrayList<>(cartItems.values());
    }

    // 获取购物车总价
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for(CartItem item : getCartItemList()) {
            totalPrice += item.getSubTotal();
        }
        return totalPrice;
    }

    // 保存购物车数据到Session中
    public static void saveToSession(HttpSession session, ShoppingCart cart) {
        session.setAttribute("cart", cart);
    }

    // 从Session中获取购物车数据，如果不存在则创建一个新的购物车
    public static ShoppingCart getFromSession(HttpSession session) {
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if(cart == null) {
            cart = new ShoppingCart();
            ShoppingCart.saveToSession(session, cart);
        }
        return cart;
    }

    // 保存购物车数据到Session中
    public void saveToSession(HttpSession session) {
        session.setAttribute("cart", this);
    }

    // 从Session中获取购物车数据，如果不存在则创建一个新的购物车
    public static ShoppingCart getFromSession(HttpSession session) {
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if(cart == null) {
            cart = new ShoppingCart();
            cart.saveToSession(session);
        }
        return cart;
    }
}
