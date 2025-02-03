

$(document).ready(function() {
    //상품삭제 ajax
    $(".btn_remove").click(function() {
        const prodNo = $(this).data("prod-no"); // 버튼에서 prodNo 값 가져오기
        if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
            // 상품 삭제 요청
            $.ajax({
                url: "/admin/product/delete/" + prodNo,
                type: "GET",
                success: function(response) {
                    // 성공적으로 삭제된 경우 페이지를 새로 고침
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert("삭제 중 오류가 발생했습니다. 다시 시도해 주세요.");
                }
            });
        }
    });

    //상품수정
    $('.btn_modify').on('click', function() {
        const prodNo = $(this).data('prod-no');
        window.location.href = "/admin/product/modify/"+prodNo; // URL 결합
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const quantityInput = document.getElementById("quantity");
    const cartQuantityInput = document.getElementById("cartQuantity"); // form 내부 hidden input
    const totalPriceElement = document.querySelector(".txt_price"); // 총 가격 업데이트용

    if (quantityInput) {
        let price = parseInt(quantityInput.getAttribute("data-price")); // 가격 가져오기

        window.changeQuantity = function (amount) {
            let currentQuantity = parseInt(quantityInput.value);
            let newQuantity = currentQuantity + amount;
            if (newQuantity < 1) newQuantity = 1; // 최소 수량 제한

            quantityInput.value = newQuantity;
            cartQuantityInput.value = newQuantity; // form 내부 hidden input 값 업데이트
            totalPriceElement.innerText = (price * newQuantity).toLocaleString() + "원";
        };

        // 사용자가 직접 입력했을 때도 cartQuantity 업데이트
        quantityInput.addEventListener("input", function () {
            let newQuantity = parseInt(quantityInput.value);
            if (isNaN(newQuantity) || newQuantity < 1) newQuantity = 1;

            cartQuantityInput.value = newQuantity; // form 내부 hidden input 값 업데이트
            totalPriceElement.innerText = (price * newQuantity).toLocaleString() + "원";
        });

        // 장바구니 제출 시 수량 동기화
        document.getElementById("cartForm").addEventListener("submit", function () {
            cartQuantityInput.value = quantityInput.value; // form 제출 전에 hidden 값 최신화
        });
    }
});