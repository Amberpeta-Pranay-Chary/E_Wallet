package org.walletService.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.walletService.model.Wallet;


/**
 * This class is used as a repository for Wallet API.
 *
 * @author rpranay665@gmail.com
 */
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByWalletId(String walletId);

    //Updating the balance in wallet based on transaction.
    @Transactional
    @Modifying
    @Query("update Wallet w set w.balance = w.balance + :amount where w.walletId = :walletId")
    void updateWallet(String walletId, Long amount);
}
